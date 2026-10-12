using System;

namespace GuildWar
{
	public interface IKeyType
	{
		string GetStringKey();

		void SetValueWithStringKey(string stringKey);
	}
	public interface IKeyType<KeyType> : IKeyType, IEquatable<KeyType>
	{
	}
}
