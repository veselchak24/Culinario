namespace Culinario_DB.EFCore.Models;

public class CommentModel : IModel<Tables.Comment>
{
    /// <summary>
    /// Уникальный идентификатор комментария
    /// </summary>
    public int Id { get; set; }

    /// <summary>
    /// Текстовое содержимое комментария
    /// </summary>
    public string Text { get; set; }

    /// <summary>
    /// Идентификатор рецепта, с которым связан комментарий
    /// </summary>
    public RecipeModel RecipeId { get; set; }

    /// <summary>
    /// Идентификатор пользователя, создавшего комментарий
    /// </summary>
    public UserModel UserId { get; set; }

    /// <summary>
    /// Временная метка создания комментария
    /// </summary>
    public DateTime TimeCreated { get; set; }

    /// <summary>
    /// Количество лайков, полученных комментарием
    /// </summary>
    public int Likes { get; set; }

    /// <summary>
    /// Количество дизлайков, полученных комментарием
    /// </summary>
    public int Dislikes { get; set; }

    /// <summary>
    /// Идентификатор комментария, на который является ответом данный комментарий, если таковой имеется
    /// </summary>
    public CommentModel? ReplyCommentId { get; set; }

    public CommentModel(Tables.Comment comment)
    {
        Id = comment.Id;
        Text = comment.Text;
        RecipeId = new RecipeModel(comment.RecipeId);
        UserId = new UserModel(comment.UserId);
        TimeCreated = comment.TimeCreated;
        Likes = comment.Likes;
        Dislikes = comment.Dislikes;
        ReplyCommentId = comment.ReplyCommentId == null ? null : new CommentModel(comment.ReplyCommentId);
    }

    public Tables.Comment ToEntity()
    {
        return new Tables.Comment
        {
            Id = Id,
            Text = Text,
            RecipeId = RecipeId.ToEntity(),
            UserId = UserId.ToEntity(),
            TimeCreated = TimeCreated,
            Likes = Likes,
            Dislikes = Dislikes,
            ReplyCommentId = ReplyCommentId?.ToEntity()
        };
    }
}