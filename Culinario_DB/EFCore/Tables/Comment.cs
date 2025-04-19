using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Culinario_DB.EFCore.Tables;

[Table("Comment")]
public class Comment : ITable
{
    /// <summary>
    /// Уникальный идентификатор комментария
    /// </summary>
    [Key]
    public int Id { get; set; }

    /// <summary>
    /// Текстовое содержимое комментария
    /// </summary>
    [MaxLength(5000)]
    public required string Text { get; set; }

    /// <summary>
    /// Идентификатор рецепта, с которым связан комментарий
    /// </summary>
    public required Recipe RecipeId { get; set; }

    /// <summary>
    /// Идентификатор пользователя, создавшего комментарий
    /// </summary>
    public required User UserId { get; set; }

    /// <summary>
    /// Временная метка создания комментария
    /// </summary>
    public required DateTime TimeCreated { get; set; }

    /// <summary>
    /// Количество лайков, полученных комментарием
    /// </summary>
    public required int Likes { get; set; }

    /// <summary>
    /// Количество дизлайков, полученных комментарием
    /// </summary>
    public required int Dislikes { get; set; }

    /// <summary>
    /// Идентификатор комментария, на который является ответом данный комментарий, если таковой имеется
    /// </summary>
    public Comment? ReplyCommentId { get; set; }
}