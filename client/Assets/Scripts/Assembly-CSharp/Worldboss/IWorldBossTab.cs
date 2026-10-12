namespace Worldboss
{
	public interface IWorldBossTab
	{
		PopupWorldboss parent { get; }

		void Initialize(PopupWorldboss parent);

		void SetActive(bool active);
	}
}
