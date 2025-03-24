using Culinario_DB.EFCore.Models;
using Culinario_DB.EFCore.Tables;
using Microsoft.EntityFrameworkCore;
using UserModel = Culinario_DB.EFCore.Models.UserModel;

namespace Culinario_DB.EFCore.Supporting_Classes;

/// <summary>
/// Класс DbHelper предоставляет методы для работы с данными пользователей в базе данных.
/// </summary>
public partial class DbHelper
{
    // /// <summary>
    // /// Получает список пользователей из базы данных.
    // /// </summary>
    // /// <returns>Список пользователей.</returns>
    // public List<UserModel> GetUsers()
    // {
    //     List<UserModel> userModels = [];
    //     var users = _context.Users
    //         .Include(user => user.UserImage)
    //         .Include(user => user.Recipes)
    //         .Include(user => user.FavouriteRecipes)
    //         .ToList();
    //     users.ForEach(user => userModels.Add(new UserModel(user)));
    //
    //     return userModels;
    // }

    /// <summary>
    /// Добавляет нового пользователя в базу данных.
    /// </summary>
    /// <param name="userModel">Модель пользователя.</param>
    /// <returns>Состояние сущности после добавления. Added, если добавление выполнено успешно.</returns>
    public EntityState AddUser(UserModel userModel)
    {
        if (_context.Users.Any(user => user.Id == userModel.Id))
            return EntityState.Unchanged;

        // UserImage
        if (userModel.UserImage != null)
            AddUserImage(userModel.UserImage, saveChanges: false);


        // Recipes & FavouriteRecipes
        userModel.Recipes?.ForEach(recipe => AddRecipe(recipe, saveChanges: false));
        userModel.FavouriteRecipes?.ForEach(recipe => AddRecipe(recipe, saveChanges: false));

        // User
        var state = _context.Users.Add(userModel.ToEntity()).State;

        if (state == EntityState.Added)
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
        var user = _context.Users
            .Include(user => user.UserImage).Include(user => user.Recipes)
            .FirstOrDefault(user => user.Id == userModel.Id);

        if (user == null) return EntityState.Unchanged;

        // UserImage

        UpdateObj<ImageModel, Image>(userModel.UserImage, user.UserImage, AddUserImage, UpdateUserImage,
            DeleteUserImage);

        // Recipes & FavouriteRecipes
        // Recipe & FavouriteRecipe == null т.к. пользователь не может добавить свой рецепт в избранное
        userModel.Recipes?.ForEach(recipe => UpdateRecipe(recipe, saveChanges: false, addIfNotExist: true));

        UpdateRangeObj(userModel.Recipes, user.Recipes, AddRecipe, UpdateRecipe, DeleteRecipe);

        userModel.FavouriteRecipes?.ForEach(recipe => UpdateRecipe(recipe, saveChanges: false, addIfNotExist: true));

        // User
        var state = _context.Users.Update(user).State;

        if (state == EntityState.Modified)
            _context.SaveChanges();

        return state;
    }

    /// <summary>
    /// Удаляет пользователя из базы данных.
    /// </summary>
    /// <param name="userId">Идентификатор пользователя.</param>
    /// <param name="isDeleteRecipe">Нужно ли удалять рецепты пользователя.</param>
    /// <returns>Состояние сущности после удаления. Deleted, если удаление выполнено успешно.</returns>
    public EntityState DeleteUser(int userId, bool isDeleteRecipe)
    {
        var user = _context.Users
            .Include(user => user.UserImage)
            .Include(user => user.Recipes)
            .Include(user => user.FavouriteRecipes)
            .FirstOrDefault(user => user.Id == userId);

        if (user == null) return EntityState.Unchanged;

        // UserImage
        if (user.UserImage != null) DeleteUserImage(user.UserImage.Id, saveChanges: false);

        // Recipes & FavouriteRecipes

        if (isDeleteRecipe)
            user.Recipes?.ForEach(recipe => DeleteRecipe(recipe.Id, saveChanges: false));

        user.FavouriteRecipes?.ForEach(recipe => DeleteRecipe(recipe.Id, saveChanges: false));

        // User
        var state = _context.Users.Remove(user).State;

        _context.SaveChanges();

        return state;
    }
}