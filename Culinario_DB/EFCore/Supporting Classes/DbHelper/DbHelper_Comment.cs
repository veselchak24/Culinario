using Microsoft.EntityFrameworkCore;
using CommentModel = Culinario_DB.EFCore.Models.CommentModel;

namespace Culinario_DB.EFCore.Supporting_Classes;

public partial class DbHelper
{
    public EntityState AddComment(CommentModel commentModel, bool saveChanges = true)
    {
        if (_context.Comments.Any(c => c.Id == commentModel.Id)
            || !_context.Users.Any(user => user.Id == commentModel.UserId.Id))
            return EntityState.Unchanged;

        var state = _context.Comments.Add(commentModel.ToEntity()).State;

        if (saveChanges) _context.SaveChanges();

        return state;
    }

    public EntityState UpdateComment(CommentModel commentModel, bool addIfNotExist = false, bool saveChanges = true)
    {
        var comment = _context.Comments.FirstOrDefault(c => c.Id == commentModel.Id);

        if (comment == null)
            return addIfNotExist ? AddComment(commentModel, saveChanges: saveChanges) : EntityState.Unchanged;
        
        var state = _context.Comments.Update(comment).State;
        
        if (saveChanges) _context.SaveChanges();
        
        return state;
    }

    public EntityState DeleteComment(int commentId, bool saveChanges = true)
    {
        var comment = _context.Comments.FirstOrDefault(c => c.Id == commentId);
        if (comment == null) return EntityState.Unchanged;
        
        var state = _context.Comments.Remove(comment).State;

        if (saveChanges) _context.SaveChanges();

        return state;
    }
}