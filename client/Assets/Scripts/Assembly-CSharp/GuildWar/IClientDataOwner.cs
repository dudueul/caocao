namespace GuildWar
{
	public interface IClientDataOwner<ClientDataType>
	{
		ClientDataType clientData { get; }
	}
}
