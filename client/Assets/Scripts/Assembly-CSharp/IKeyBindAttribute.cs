public interface IKeyBindAttribute
{
	KeyBindData defaultSetting { get; }

	string description { get; }

	bool editable { get; }
}
