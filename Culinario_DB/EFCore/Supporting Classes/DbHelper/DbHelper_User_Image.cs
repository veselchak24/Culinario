using Microsoft.EntityFrameworkCore;
using UserImageModel = Culinario_DB.EFCore.Models.ImageModel;

namespace Culinario_DB.EFCore.Supporting_Classes;

public partial class DbHelper
{
    public EntityState AddUserImage(UserImageModel imageModel, bool saveChanges = true)
    {
        if (imageModel.ToEntity() is not Tables.UserImage imageEntity)
            return EntityState.Unchanged;

        if (_context.UserImages.Any(image => image.Id == imageModel.Id)
            || !_context.Users.Any(image => image.Id == imageEntity.User.Id))
            return EntityState.Unchanged;

        var state = _context.UserImages.Add(imageEntity).State;

        if (saveChanges && state == EntityState.Added)
            _context.SaveChanges();

        return state;
    }

    public EntityState UpdateUserImage(UserImageModel imageModel, bool addIfNotExist = false, bool saveChanges = true)
    {
        var image = _context.UserImages.FirstOrDefault(image => image.Id == imageModel.Id);

        if (image == null)
            return addIfNotExist ? AddUserImage(imageModel, saveChanges) : EntityState.Unchanged;

        var state = _context.UserImages.Update(image).State;

        if (saveChanges && state == EntityState.Modified)
            _context.SaveChanges();

        return state;
    }

    public EntityState DeleteUserImage(int imageModelId, bool saveChanges = true)
    {
        var image = _context.UserImages.FirstOrDefault(image => image.Id == imageModelId);

        if (image == null)
            return EntityState.Unchanged;

        var state = _context.UserImages.Remove(image).State;

        if (saveChanges && state == EntityState.Deleted)
            _context.SaveChanges();

        return state;
    }
}