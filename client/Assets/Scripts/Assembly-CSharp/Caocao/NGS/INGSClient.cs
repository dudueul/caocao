namespace Caocao.NGS
{
	public interface INGSClient
	{
		void StandBy();

		void Install();

		void Process(byte[] data, uint sessionID);

		void Uninstall();

		void SendMessage();
	}
}
