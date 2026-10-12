using System;
using System.Collections.Generic;

namespace BattleRecorder
{
	public static class File
	{
		public static Dictionary<byte, Func<OptimizedBinaryReader, IWritable>> ms_readers;

		static File()
		{
		}
	}
}
