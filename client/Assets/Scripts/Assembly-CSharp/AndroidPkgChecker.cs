public class AndroidPkgChecker
{
	public enum Result
	{
		None = 0,
		Installed = 1,
		PackageInfoNotFound = 2,
		LabelMistached = 3,
		ExceptionOccurred = 4
	}

	public static bool IsPlayStoreInstalled()
	{
		return false;
	}

	public static Result IsPkgInstalled(string packageName, string filterString = "Market")
	{
		return Result.None;
	}
}
