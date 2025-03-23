using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

// ReSharper disable CheckNamespace
namespace Culinario_DB.EFCore.Tables;

[Table("Product")]
public class Product
{
    /// <summary>
    /// Идентификатор продукта
    /// </summary>
    [Key]
    public int Id { get; set; }

    /// <summary>
    /// Название продукта
    /// </summary>
    [MaxLength(100)]
    public required string Name { get; set; }

    /// <summary>
    /// Описание продукта
    /// </summary>
    [MaxLength(1000)]
    public required string Description { get; set; }

    /// <summary>
    /// Пищевая ценность продукта
    /// </summary>
    public required string Nutritional { get; set; } // заменить в моделях

    /// <summary>
    /// Категория продукта
    /// </summary>
    public required ProductCategory CategoryId { get; set; }
}