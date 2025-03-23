namespace Culinario_DB.EFCore.Models;

public class RecipeModel : IModel<Tables.Recipe>
{
    public int Id { get; set; }

    /// <summary>
    /// Название рецепта
    /// </summary>
    public string Name { get; set; }

    /// <summary>
    /// Описание рецепта
    /// </summary>
    public string Description { get; set; }

    /// <summary>
    /// Категория рецепта
    /// </summary>
    public RecipeCategoryModel Category { get; set; }

    /// <summary>
    /// Изображение рецепта
    /// </summary>
    public ImageModel? Image { get; set; }

    /// <summary>
    /// Пользователь, добавивший рецепт
    /// </summary>
    public UserModel UserId { get; set; }

    /// <summary>
    /// Список продуктов для рецепта
    /// </summary>
    public List<ProductModel> Products { get; set; }

    /// <summary>
    /// Шаги приготовления рецепта
    /// </summary>
    public List<RecipeStepsModel>? Steps { get; set; }

    /// <summary>
    /// Количество лайков рецепта
    /// </summary>
    public int Likes { get; set; }

    /// <summary>
    /// Количество дизлайков рецепта
    /// </summary>
    public int Dislikes { get; set; }

    /// <summary>
    /// Количество просмотров рецепта
    /// </summary>
    public int Views { get; set; }

    /// <summary>
    /// Комментарии к рецепту
    /// </summary>
    public List<CommentModel>? Comments { get; set; }

    public RecipeModel(Tables.Recipe recipe)
    {
        Id = recipe.Id;
        Name = recipe.Name;
        Description = recipe.Description;
        Category = new RecipeCategoryModel(recipe.Category);
        Image = recipe.Image == null ? null : new ImageModel(recipe.Image);
        UserId = new UserModel(recipe.UserId);
        Products = recipe.Products.ConvertAll(p => new ProductModel(p));
        Steps = recipe.Steps?.ConvertAll(s => new RecipeStepsModel(s));
        Likes = recipe.Likes;
        Dislikes = recipe.Dislikes;
        Views = recipe.Views;
        Comments = recipe.Comments?.ConvertAll(c => new CommentModel(c));
    }

    public Tables.Recipe ToEntity()
    {
        return new Tables.Recipe()
        {
            Id = Id,
            Name = Name,
            Description = Description,
            Category = Category.ToEntity(),
            Image = Image?.ToEntity() as Tables.RecipeImage,
            UserId = UserId.ToEntity(),
            Products = Products.ConvertAll(p => p.ToEntity()),
            Steps = Steps?.ConvertAll(s => s.ToEntity()),
            Likes = Likes,
            Dislikes = Dislikes,
            Views = Views,
            Comments = Comments?.ConvertAll(c => c.ToEntity())
        };
    }
}