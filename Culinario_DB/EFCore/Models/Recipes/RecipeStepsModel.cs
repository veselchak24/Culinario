namespace Culinario_DB.EFCore.Models;

public class RecipeStepsModel : IModel<Tables.RecipeSteps>
{
    public int Id { get; set; }
    public RecipeModel RecipeId { get; set; }
    public string Description { get; set; }
    public List<ImageModel>? Images { get; set; }

    public RecipeStepsModel(Tables.RecipeSteps steps)
    {
        RecipeId = new RecipeModel(steps.Recipe);
        Description = steps.Description;
        Images = steps.Images?.ConvertAll(x => new ImageModel(x));
    }

    public Tables.RecipeSteps ToEntity()
    {
        return new Tables.RecipeSteps
        {
            Id = Id,
            Recipe = RecipeId.ToEntity(),
            Description = Description,
            Images = Images?.ConvertAll(img => (img.ToEntity() as Tables.RecipeImage)!)
        };
    }
}