namespace Thingsoft
{
	public interface IPoolElement
	{
		bool isRestored();

		void MarkRestored();

		void MarkUsed();
	}
}
