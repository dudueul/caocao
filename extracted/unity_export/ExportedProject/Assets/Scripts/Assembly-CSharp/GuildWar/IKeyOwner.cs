namespace GuildWar
{
	public interface IKeyOwner<KeyType> where KeyType : struct, IKeyType
	{
		KeyType GetKey();
	}
}
