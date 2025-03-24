using System.ComponentModel.DataAnnotations.Schema;

namespace Culinario_DB.EFCore.Tables;

[Table("UserImage")]
public class UserImage : Image
{
    public required User UserId { get; set; }
}