public interface IPatchView
{
	void ProgressTitle(string progress, object param = null);

	void ProgressBar(float progress);

	void ProgressBytes(long downloaded, long total);

	void DecompressCount(int count, int totalCount);

	void SetActive(bool active);

	void Finish();

	bool IsClosed();
}
