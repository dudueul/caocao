using System.Collections.Generic;
using System.IO;
using System.Runtime.InteropServices;
using ICSharpCode.SharpZipLib.Zip;

public class SyncStreamingAssetsLoader : Singleton<SyncStreamingAssetsLoader>
{
	[StructLayout((LayoutKind)0, Size = 16)]
	private struct Entry
	{
		public long index;

		public long size;

		public Entry(long index, long size)
		{
			this.index = 0L;
			this.size = 0L;
		}
	}

	private Dictionary<string, Entry> _entries;

	private FileStream _fs;

	private ZipFile _zipFile;

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	public bool Exists(string filePath)
	{
		return false;
	}

	public byte[] Load(string filePath)
	{
		return null;
	}
}
