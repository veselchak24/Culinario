using Culinario_DB.EFCore.Tables;
using Microsoft.EntityFrameworkCore;
using RecipeStepsModel = Culinario_DB.EFCore.Models.RecipeStepsModel;

namespace Culinario_DB.EFCore.Supporting_Classes;

public partial class DbHelper
{
    public EntityState AddStep(RecipeStepsModel stepModel, bool saveChanges = true)
    {
        if (_context.RecipeSteps.Any(step => step.Id == stepModel.Id)
            || !_context.Recipes.Any(recipe => recipe.Id == stepModel.RecipeId.Id))
            return EntityState.Unchanged;

        stepModel.Images?.ForEach(image => AddRecipeImage(image, saveChanges: false));

        var state = _context.RecipeSteps.Add(stepModel.ToEntity()).State;

        if (saveChanges) _context.SaveChanges();

        return state;
    }

    public EntityState UpdateStep(RecipeStepsModel stepModel, bool addIfNotExist = false, bool saveChanges = true)
    {
        var step = _context.RecipeSteps
            .Include(recipeSteps => recipeSteps.Images)
            .FirstOrDefault(step => step.Id == stepModel.Id);

        if (step == null) return addIfNotExist ? AddStep(stepModel, saveChanges: saveChanges) : EntityState.Unchanged;

        // Images
        UpdateRangeObj(stepModel.Images, step.Images?.ConvertAll<Image>(image => image), AddRecipeImage,
            UpdateRecipeImage,
            DeleteRecipeImage);

        // Step
        var state = _context.Update(step).State;

        if (saveChanges) _context.SaveChanges();

        return state;
    }

    public EntityState DeleteStep(int stepId, bool saveChanges = true)
    {
        var step = _context.RecipeSteps
            .Include(recipeSteps => recipeSteps.Images)
            .FirstOrDefault(step => step.Id == stepId);

        if (step == null) return EntityState.Unchanged;

        // Images
        step.Images?.ForEach(image => DeleteRecipeImage(image.Id, saveChanges: false));

        // Step
        var state = _context.Remove(step).State;

        if (saveChanges) _context.SaveChanges();

        return state;
    }
}