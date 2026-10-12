using LitJson;

public class JsonImporterPro<T, I> : JsonImporter where T : JsonImporterPro<T, I>, I
{
	public delegate void UpdatedHandler(I updatedData);

	private UpdatedHandler OnUpdated__BackingField;

	public event UpdatedHandler OnUpdated
	{
		add
		{
		}
		remove
		{
		}
	}

	public override void Import(JsonData jsonData)
	{
	}
}
