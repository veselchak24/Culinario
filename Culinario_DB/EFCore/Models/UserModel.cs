namespace Culinario_DB.EFCore.Models;

public class UserModel : IModel<Tables.User>
{
    public int Id { get; set; }

    /// <summary>
    /// Имя пользователя
    /// </summary>
    public string Name { get; set; }

    /// <summary>
    /// Email пользователя
    /// </summary>
    public string? Email { get; set; }

    /// <summary>
    /// Аватар пользователя
    /// </summary>
    public ImageModel? UserImage { get; set; }

    /// <summary>
    /// Рецепты пользователя
    /// </summary>
    public List<RecipeModel>? Recipes { get; set; }

    /// <summary>
    /// Избранные рецепты пользователя
    /// </summary>
    public List<RecipeModel>? FavouriteRecipes { get; set; }

    public UserModel(Tables.User user)
    {
        Id = user.Id;
        Name = user.Name;
        Email = user.Email;
        UserImage = user.UserImage == null ? null : new ImageModel(user.UserImage);
        Recipes = user.Recipes?.ConvertAll(r => new RecipeModel(r));
        FavouriteRecipes = user.FavouriteRecipes?.ConvertAll(r => new RecipeModel(r));
    }

    public Tables.User ToEntity()
    {
        return new Tables.User
        {
            Id = Id,
            Name = Name,
            Email = Email,
            UserImage = UserImage?.ToEntity() as Tables.UserImage,
            Recipes = Recipes?.ConvertAll(r => r.ToEntity()),
            FavouriteRecipes = FavouriteRecipes?.ConvertAll(r => r.ToEntity())
        };
    }
}