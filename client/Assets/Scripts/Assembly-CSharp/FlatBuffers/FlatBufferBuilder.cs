namespace FlatBuffers
{
	public class FlatBufferBuilder
	{
		private int _space;

		private ByteBuffer _bb;

		private int _minAlign;

		private int[] _vtable;

		private int _vtableSize;

		private int _objectStart;

		private int[] _vtables;

		private int _numVtables;

		private int _vectorNumElems;

		public bool ForceDefaults { get; set; }

		public int Offset
		{
			get
			{
				return 0;
			}
		}

		public ByteBuffer DataBuffer
		{
			get
			{
				return null;
			}
		}

		public FlatBufferBuilder(int initialSize)
		{
		}

		public void Clear()
		{
		}

		public void Pad(int size)
		{
		}

		private void GrowBuffer()
		{
		}

		public void Prep(int size, int additionalBytes)
		{
		}

		public void PutBool(bool x)
		{
		}

		public void PutSbyte(sbyte x)
		{
		}

		public void PutByte(byte x)
		{
		}

		public void PutShort(short x)
		{
		}

		public void PutUshort(ushort x)
		{
		}

		public void PutInt(int x)
		{
		}

		public void PutUint(uint x)
		{
		}

		public void PutLong(long x)
		{
		}

		public void PutUlong(ulong x)
		{
		}

		public void PutFloat(float x)
		{
		}

		public void PutDouble(double x)
		{
		}

		public void AddBool(bool x)
		{
		}

		public void AddSbyte(sbyte x)
		{
		}

		public void AddByte(byte x)
		{
		}

		public void AddShort(short x)
		{
		}

		public void AddUshort(ushort x)
		{
		}

		public void AddInt(int x)
		{
		}

		public void AddUint(uint x)
		{
		}

		public void AddLong(long x)
		{
		}

		public void AddUlong(ulong x)
		{
		}

		public void AddFloat(float x)
		{
		}

		public void AddDouble(double x)
		{
		}

		public void AddOffset(int off)
		{
		}

		public void StartVector(int elemSize, int count, int alignment)
		{
		}

		public VectorOffset EndVector()
		{
			return default(VectorOffset);
		}

		public VectorOffset CreateVectorOfTables<T>(Offset<T>[] offsets) where T : struct
		{
			return default(VectorOffset);
		}

		public void Nested(int obj)
		{
		}

		public void NotNested()
		{
		}

		public void StartObject(int numfields)
		{
		}

		public void Slot(int voffset)
		{
		}

		public void AddBool(int o, bool x, bool d)
		{
		}

		public void AddSbyte(int o, sbyte x, sbyte d)
		{
		}

		public void AddByte(int o, byte x, byte d)
		{
		}

		public void AddShort(int o, short x, int d)
		{
		}

		public void AddUshort(int o, ushort x, ushort d)
		{
		}

		public void AddInt(int o, int x, int d)
		{
		}

		public void AddUint(int o, uint x, uint d)
		{
		}

		public void AddLong(int o, long x, long d)
		{
		}

		public void AddUlong(int o, ulong x, ulong d)
		{
		}

		public void AddFloat(int o, float x, double d)
		{
		}

		public void AddDouble(int o, double x, double d)
		{
		}

		public void AddOffset(int o, int x, int d)
		{
		}

		public StringOffset CreateString(string s)
		{
			return default(StringOffset);
		}

		public void AddStruct(int voffset, int x, int d)
		{
		}

		public int EndObject()
		{
			return 0;
		}

		public void Required(int table, int field)
		{
		}

		protected void Finish(int rootTable, bool sizePrefix)
		{
		}

		public void Finish(int rootTable)
		{
		}

		public void FinishSizePrefixed(int rootTable)
		{
		}

		public byte[] SizedByteArray()
		{
			return null;
		}

		protected void Finish(int rootTable, string fileIdentifier, bool sizePrefix)
		{
		}

		public void Finish(int rootTable, string fileIdentifier)
		{
		}

		public void FinishSizePrefixed(int rootTable, string fileIdentifier)
		{
		}
	}
}
