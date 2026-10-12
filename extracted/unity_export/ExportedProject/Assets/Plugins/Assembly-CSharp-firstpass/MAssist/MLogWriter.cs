using System;

namespace MAssist
{
	[Serializable]
	public abstract class MLogWriter
	{
		public abstract void Init();

		public abstract void SetDefault();

		public abstract void Write(MLogData data);
	}
}
