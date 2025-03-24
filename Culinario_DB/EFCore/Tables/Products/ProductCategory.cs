using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Culinario_DB.EFCore.Tables;

[Table("ProductCategory")]
public class ProductCategory : ITable
{
    [Key] public int Id { get; set; }

    [MaxLength(50)] public required string Name { get; set; }

    public required byte[] Image { get; set; }
}