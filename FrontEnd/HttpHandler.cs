using System.Net.Http.Json;

namespace SportCenter;

public static class HttpHandler
{
    private static readonly HttpClient SharedClient = new()
    {
        BaseAddress = new Uri("http://localhost:8080"),
    };
    
    public static async Task<T> Get<T>(string url)
    {
        var response = await SharedClient.GetAsync(url);
        response.EnsureSuccessStatusCode();
        return await response.Content.ReadFromJsonAsync<T>();
    }
    
    public static async Task<T> Post<T>(string url, object data)
    {
        var response = await SharedClient.PostAsJsonAsync(url, data);
        response.EnsureSuccessStatusCode();
        return await response.Content.ReadFromJsonAsync<T>();
    }
    
    public static async Task<T> Put<T>(string url, object data)
    {
        var response = await SharedClient.PutAsJsonAsync(url, data);
        response.EnsureSuccessStatusCode();
        return await response.Content.ReadFromJsonAsync<T>();
    }
    
    public static async Task<T> Delete<T>(string url)
    {
        var response = await SharedClient.DeleteAsync(url);
        response.EnsureSuccessStatusCode();
        return await response.Content.ReadFromJsonAsync<T>();
    }
}