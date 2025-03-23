namespace Culinario_DB.EFCore.Models;

public class RecipeCategoryModel : IModel<Tables.RecipeCategory>
{
    public int Id { get; set; }

    public string Name { get; set; }

    public RecipeCategoryModel(Tables.RecipeCategory category)
    {
        Id = category.Id;
        Name = category.Name;
    }

    public Tables.RecipeCategory ToEntity()
    {
        return new Tables.RecipeCategory
        {
            Id = Id,
            Name = Name
        };
    }
}