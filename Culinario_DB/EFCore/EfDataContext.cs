using Microsoft.EntityFrameworkCore;
using Culinario_DB.EFCore.Tables;

namespace Culinario_DB.EFCore;

public class EfDataContext : DbContext
{
    //Images
    public DbSet<RecipeImage> RecipeImages { get; set; }
    public DbSet<UserImage> UserImages { get; set; }

    // Products
    public DbSet<Product> Products { get; set; }
    public DbSet<ProductCategory> ProductCategories { get; set; }

    // Recipes
    public DbSet<Recipe> Recipes { get; set; }
    public DbSet<RecipeCategory> RecipeCategories { get; set; }
    public DbSet<RecipeSteps> RecipeSteps { get; set; }

    public DbSet<User> Users { get; set; }
    public DbSet<Comment> Comments { get; set; }

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