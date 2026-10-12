using System;
using System.Collections;
using LitJson;

public interface IPatchProcessor
{
	AssetBundleList assetBundleList { get; }

	void SetAssetBundleList(JsonData json);

	string GetAssetBundlePassword();

	string GetAssetBundleVersion();

	void RegisterView(IPatchView view);

	void UnregisterView(IPatchView view);

	IEnumerator WaitReceivingAssetBundleList();

	IEnumerator BuildAssetBundleList();

	IEnumerator DownloadAssetBundles(DateTime eventScriptDate);

	IEnumerator PostProcess();

	string GetForeignPath();

	AssetBundleListInfo GetBundleListInfo();

	string GetLocalFilePath(string fileName);
}
