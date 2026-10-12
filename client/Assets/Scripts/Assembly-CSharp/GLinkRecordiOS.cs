public class GLinkRecordiOS : IGLinkRecord
{
	private delegate void RecordStartDelegate();

	private delegate void RecordErrorDelegate(string result);

	private delegate void RecordFinishDelegate();

	private delegate void RecordFinishWithPreviewDelegate();

	public static void _RecordStartCallback()
	{
	}

	public static void _RecordErrorCallback(string result)
	{
	}

	public static void _RecordFinishCallback()
	{
	}

	public static void _RecordFinishWithPreviewCallback()
	{
	}

	public void startRecord()
	{
	}

	public void stopRecord()
	{
	}
}
