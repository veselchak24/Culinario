namespace Culinario_DB.EFCore.Models;

public class ImageModel : IModel<Tables.Image>
{
    public int Id { get; set; }
    public byte[] Data { get; set; }

    public ImageModel(Tables.Image image)
    {
        Id = image.Id;
        Data = image.Data;
    }

    public Tables.Image ToEntity()
    {
        return new Tables.Image { Id = Id, Data = Data };
    }
}