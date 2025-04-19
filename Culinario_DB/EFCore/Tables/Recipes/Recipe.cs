using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

// ReSharper disable CheckNamespace
namespace Culinario_DB.EFCore.Tables;

[Table("Recipe")]
public class Recipe : ITable
{
    /// <summary>
    /// Идентификатор рецепта
    /// </summary>
    [Key]
    public int Id { get; set; }

    /// <summary>
    /// Название рецепта
    /// </summary>
    [StringLength(100)]
    public required string Name { get; set; }

    /// <summary>
    /// Описание рецепта
    /// </summary>
    [StringLength(500)]
    public required string Description { get; set; }

    /// <summary>
    /// Категория рецепта
    /// </summary>
    public required RecipeCategory Category { get; set; }

    /// <summary>
    /// Изображение рецепта
    /// </summary>
    public RecipeImage? Image { get; set; }

    /// <summary>
    /// Пользователь, добавивший рецепт
    /// </summary>
    public required User User { get; set; }

    /// <summary>
    /// Список продуктов для рецепта
    /// </summary>
    public required List<Product> Products { get; set; }

    /// <summary>
    /// Шаги приготовления рецепта
    /// </summary>
    public List<RecipeSteps>? Steps { get; set; }

    /// <summary>
    /// Количество лайков рецепта
    /// </summary>
    public uint Likes { get; set; }

    /// <summary>
    /// Количество дизлайков рецепта
    /// </summary>
    public uint Dislikes { get; set; }

    /// <summary>
    /// Количество просмотров рецепта
    /// </summary>
    public uint Views { get; set; }

    /// <summary>
    /// Комментарии к рецепту
    /// </summary>
    public List<Comment>? Comments { get; set; }
}