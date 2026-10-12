using System;
using System.Collections.Generic;
using System.IO;

public class AssetBundleListInfo : JsonImporter, IJsonExportable
{
	public string cdnPath;

	public int buildVersion;

	public List<AssetBundlePackage> packages;

	public List<AssetBundlePackage> seriesStoryPackages;

	public Dictionary<string, List<string>> dependency;

	public void Clear()
	{
	}

	public List<AssetBundleInfo> GetAssetBundleInfo()
	{
		return null;
	}

	public string GetPackageFileName(AssetBundlePackage package)
	{
		return null;
	}

	private void UpdateAddedFiles(Dictionary<string, FileInfo> existingFiles, List<AssetBundlePackage> downloadList, DateTime eventScriptDate)
	{
	}

	private void UpdateModifiedFiles(Dictionary<string, FileInfo> existingFiles, List<AssetBundlePackage> downloadList, List<string> deleteList, List<string> exceptList)
	{
	}

	public void UpateFileList(Dictionary<string, FileInfo> existingFiles, ref List<AssetBundlePackage> downloadList, ref List<string> deleteList, List<string> exceptList, DateTime eventScriptDate)
	{
	}

	public string FineBundleNameByAssetName(string assetName)
	{
		return null;
	}

	public bool ExistSeriseStoryPackages(DateTime openDate)
	{
		return false;
	}
}
