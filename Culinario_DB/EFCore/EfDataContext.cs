using Microsoft.EntityFrameworkCore;
using Culinario_DB.EFCore.Tables;

namespace Culinario_DB.EFCore;

public class EfDataContext : DbContext
{
    public DbSet<User> Users { get; set; }
    public DbSet<Recipe> Orders { get; set; }
    public DbSet<Product> Products { get; set; }
    public DbSet<RecipeCategory> CategoryRecipes { get; set; }

    private readonly IConfiguration _configuration;

    public EfDataContext(DbContextOptions<EfDataContext> options, IConfiguration config) : base(options)
    {
        _configuration = config;
    }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        // from appsettings.json
        optionsBuilder.UseNpgsql(_configuration.GetConnectionString("DefaultConnection"));
    }
}