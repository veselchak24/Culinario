using System.ComponentModel.DataAnnotations;

namespace Culinario_DB.EFCore.Tables;

// <summary>
// class for tables images
// </summary>
public class Image : ITable
{
    [Key] public int Id { get; set; }
    public required byte[] Data { get; set; }
}