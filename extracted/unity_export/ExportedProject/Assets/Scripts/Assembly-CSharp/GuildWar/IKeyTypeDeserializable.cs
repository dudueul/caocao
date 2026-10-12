using System;
using FlatBuffers;

namespace GuildWar
{
	public interface IKeyTypeDeserializable<KeyType, FlatBufferDataType, FlatBufferKeyType> : IKeyType<KeyType>, IKeyType, IEquatable<KeyType> where FlatBufferDataType : struct, IFlatbufferObject where FlatBufferKeyType : struct, IFlatbufferObject
	{
		void DeserializeKey(FlatBufferKeyType data);

		void DeserializeKeyFromData(FlatBufferDataType data);
	}
}
