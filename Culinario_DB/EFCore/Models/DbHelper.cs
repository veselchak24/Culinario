using Microsoft.EntityFrameworkCore;

namespace Culinario_DB.EFCore.Models;

/// <summary>
/// Класс DbHelper предоставляет методы для работы с данными пользователей в базе данных.
/// </summary>
public class DbHelper
{
    private readonly EfDataContext _context;

    public DbHelper(EfDataContext context)
    {
        _context = context;
    }

    /// <summary>
    /// Получает список пользователей из базы данных.
    /// </summary>
    /// <returns>Список пользователей.</returns>
    public List<UserModel> GetUsers()
    {
        List<UserModel> userModels = [];
        var users = _context.Users
            .Include(user => user.UserImage)
            .Include(user => user.Recipes)
            .Include(user => user.FavouriteRecipes)
            .ToList();
        users.ForEach(user => userModels.Add(new UserModel(user)));

        return userModels;
    }

    /// <summary>
    /// Добавляет нового пользователя в базу данных.
    /// </summary>
    /// <param name="userModel">Модель пользователя.</param>
    /// <returns>Состояние сущности после добавления. Added, если добавление выполнено успешно.</returns>
    public EntityState AddUser(UserModel userModel)
    {
        if (_context.Users.FirstOrDefault(user => user!.Id == userModel.Id) != null)
            return EntityState.Unchanged;

        var state = _context.Users.Add(userModel.ToEntity()).State;

        _context.SaveChanges();
        return state;
    }

    /// <summary>
    /// Обновляет данные пользователя в базе данных.
    /// </summary>
    /// <param name="userModel">Модель пользователя.</param>
    /// <returns>Состояние сущности после обновления. Modified, если обновление выполнено успешно.</returns>
    public EntityState UpdateUser(UserModel userModel)
    {
        var user = _context.Users.FirstOrDefault(user => user!.Id == userModel.Id);

        if (user == null) return EntityState.Unchanged;

        _context.Users.Update(userModel.ToEntity());
        _context.SaveChanges();

        return EntityState.Modified;
    }

    /// <summary>
    /// Удаляет пользователя из базы данных.
    /// </summary>
    /// <param name="userId">Идентификатор пользователя.</param>
    /// <returns>Состояние сущности после удаления. Deleted, если удаление выполнено успешно.</returns>
    public EntityState DeleteUser(int userId)
    {
        var user = _context.Users.FirstOrDefault(user => user!.Id == userId);

        if (user == null) return EntityState.Unchanged;

        _context.Users.Remove(user);
        _context.SaveChanges();
        return EntityState.Deleted;
    }
}