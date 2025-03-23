using System.ComponentModel.DataAnnotations;

// ReSharper disable CheckNamespace
namespace Culinario_DB.EFCore.Tables;

// <summary>
// class for tables images
// </summary>
public class Image
{
    [Key] public int Id { get; set; }
    public required byte[] Data { get; set; }
}