using Microsoft.EntityFrameworkCore;
using RecipeModel = Culinario_DB.EFCore.Models.RecipeModel;

namespace Culinario_DB.EFCore.Supporting_Classes;

public partial class DbHelper
{
    public RecipeModel? GetRecipe(int id)
    {
        var recipe = _context.Recipes
            .Include(recipe => recipe.Category)
            .Include(recipe => recipe.Image)
            .Include(recipe => recipe.User)
            .Include(recipe => recipe.Products)
            .Include(recipe => recipe.Steps)
            .Include(recipe => recipe.Comments)
            .FirstOrDefault(recipe => recipe.Id == id);

        return recipe != null ? new RecipeModel(recipe): null;
    }


    /// <summary>
    /// Добавляет рецепт в базу данных.
    /// Если рецепт добавлен, возвращает EntityState.Added
    /// </summary>
    /// <param name="recipeModel">Модель рецепта</param>
    /// <param name="saveChanges">Сохранить изменения в базе данных</param>
    /// <returns>EntityState</returns>
    public EntityState AddRecipe(RecipeModel recipeModel, bool saveChanges = true)
    {
        if (_context.Recipes.Any(recipe => recipe.Id == recipeModel.Id)
            || !_context.Users.Any(user => user.Id == recipeModel.UserId.Id))
            return EntityState.Unchanged;

        // Image
        if (recipeModel.Image != null)
            AddRecipeImage(recipeModel.Image, saveChanges: false);

        // Steps
        recipeModel.Steps?.ForEach(step => AddStep(step, saveChanges: false));

        // Comments
        recipeModel.Comments?.ForEach(comment => AddComment(comment, saveChanges: false));

        // Recipe
        var state = _context.Recipes.Add(recipeModel.ToEntity()).State;

        if (saveChanges && state == EntityState.Added)
            _context.SaveChanges();

        return state;
    }

    public EntityState UpdateRecipe(RecipeModel recipeModel, bool addIfNotExist = false, bool saveChanges = true)
    {
        var recipe = _context.Recipes
            .Include(recipe => recipe.Image)
            .Include(recipe => recipe.Steps)
            .Include(recipe => recipe.Comments)
            .FirstOrDefault(recipe => recipe.Id == recipeModel.Id);

        if (recipe == null)
            return addIfNotExist ? AddRecipe(recipeModel, saveChanges: saveChanges) : EntityState.Unchanged;

        // Image
        UpdateObj<Models.ImageModel, Tables.Image>(recipeModel.Image, recipe.Image, AddRecipeImage, UpdateRecipeImage,
            DeleteRecipeImage);

        // Steps
        UpdateRangeObj(recipeModel.Steps, recipe.Steps, AddStep, UpdateStep, DeleteStep);

        // Comments
        // по идее должно быть null
        if (recipeModel.Comments != null)
        {
            if (recipe.Comments != null)
                recipeModel.Comments.ForEach(comment =>
                    UpdateComment(comment, addIfNotExist: true, saveChanges: false));
            else
                recipeModel.Comments.ForEach(comment => AddComment(comment, saveChanges: false));
        }

        // Recipe
        var state = _context.Recipes.Update(recipe).State;

        if (saveChanges) _context.SaveChanges();

        return state;
    }

    public EntityState DeleteRecipe(int recipeId, bool saveChanges = true)
    {
        var recipe = _context.Recipes
            .Include(recipe => recipe.Image)
            .Include(recipe => recipe.Steps)
            .Include(recipe => recipe.Comments)
            .FirstOrDefault(recipe => recipe.Id == recipeId);

        if (recipe == null) return EntityState.Unchanged;

        // Image
        if (recipe.Image != null)
            DeleteRecipeImage(recipe.Image.Id, saveChanges: false);

        // Steps
        recipe.Steps?.ForEach(step => DeleteStep(step.Id, saveChanges: false));

        // Comments
        recipe.Comments?.ForEach(comment => DeleteComment(comment.Id, saveChanges: false));

        // Recipe
        var state = _context.Recipes.Remove(recipe).State;

        if (saveChanges && state == EntityState.Deleted) _context.SaveChanges();

        return state;
    }
}