using System.Collections.Generic;
using System.Net.Security;
using System.Security.Cryptography.X509Certificates;

public static class PatcherUtil
{
	private static string ms_patchPath;

	private static readonly char[] ms_pathDel;

	public static string GetLocalFilePathFromUrl(string url)
	{
		return null;
	}

	public static string GetCachePatchPath()
	{
		return null;
	}

	public static string GetPatchPath()
	{
		return null;
	}

	public static void SaveBinaryFile(byte[] byteData, string path)
	{
	}

	public static byte[] Compress(string text)
	{
		return null;
	}

	public static string Decompress(byte[] compressed)
	{
		return null;
	}

	public static string DecryptAndDecompress(string filePath, string password)
	{
		return null;
	}

	public static string DecryptAndDecompress(byte[] bytes, string password)
	{
		return null;
	}

	public static string GetBundleTarget()
	{
		return null;
	}

	public static string GetPreferredTextureFormat()
	{
		return null;
	}

	public static string GetSystemInfo()
	{
		return null;
	}

	public static long GetTotalDownloadedBytes(List<AssetBundlePackage> downloadList)
	{
		return 0L;
	}

	public static long GetTotalWritedBytes(List<AssetBundlePackage> downloadList)
	{
		return 0L;
	}

	public static AssetBundleListInfo RequestLiveBundleList(string buildTarget, string baseServer = "")
	{
		return null;
	}

	public static bool Vaildator(object sender, X509Certificate certificate, X509Chain chain, SslPolicyErrors sslPolicyErrors)
	{
		return false;
	}

	public static string GetDefaultLocalizationBundleFilePath()
	{
		return null;
	}

	public static string GetFileName(string path)
	{
		return null;
	}

	private static void SetNoBackupFlag(string path)
	{
	}
}
