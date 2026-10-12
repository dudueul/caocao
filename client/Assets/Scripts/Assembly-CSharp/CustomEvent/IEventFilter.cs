using LitJson;

namespace CustomEvent
{
	public interface IEventFilter
	{
		void Parse(JsonData jsonData);

		bool Check(params object[] param);
	}
}
