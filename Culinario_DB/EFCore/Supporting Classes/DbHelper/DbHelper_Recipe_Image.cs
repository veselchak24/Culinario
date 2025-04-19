using Microsoft.EntityFrameworkCore;
using RecipeImageModel = Culinario_DB.EFCore.Models.ImageModel;

namespace Culinario_DB.EFCore.Supporting_Classes;

public partial class DbHelper
{
    public EntityState AddRecipeImage(RecipeImageModel imageModel, bool saveChanges = true)
    {
        if (imageModel.ToEntity() is not Tables.RecipeImage imageEntity)
            return EntityState.Unchanged;

        if (_context.RecipeImages.Any(image => image.Id == imageModel.Id)
            || !_context.Recipes.Any(image => image.Id == imageEntity.Recipe.Id))
            return EntityState.Unchanged;

        var state = _context.RecipeImages.Add(imageEntity).State;

        if (saveChanges && state == EntityState.Added)
            _context.SaveChanges();

        return state;
    }

    public EntityState UpdateRecipeImage(RecipeImageModel imageModel, bool addIfNotExist = false, bool saveChanges = true)
    {
        var image = _context.RecipeImages.FirstOrDefault(image => image.Id == imageModel.Id);

        if (image == null)
            return addIfNotExist ? AddRecipeImage(imageModel, saveChanges) : EntityState.Unchanged;

        var state = _context.RecipeImages.Update(image).State;

        if (saveChanges && state == EntityState.Modified)
            _context.SaveChanges();

        return state;
    }

    public EntityState DeleteRecipeImage(int imageModelId, bool saveChanges = true)
    {
        var image = _context.RecipeImages.FirstOrDefault(image => image.Id == imageModelId);

        if (image == null)
            return EntityState.Unchanged;

        var state = _context.RecipeImages.Remove(image).State;

        if (saveChanges && state == EntityState.Deleted)
            _context.SaveChanges();

        return state;
    }
}