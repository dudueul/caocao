using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class MemoryManager : MonoBehaviour
{
	public static MemoryManager instance;

	public float threshold;

	private float m_baseUsedMono;

	private float m_maxUsedMono;

	private const long megabyte = 1048576L;

	private const string logFormatStrInfo = "MemoryManager:: monoUsed : {0:0.##}";

	private const string logFormatStrInformGC = "MemoryManager:: GC.collect will be called. base : {0}, current : {1}, max : {2}, threshold : {3}";

	private const string logFormatStrDescHdr = "MemoryManager::desc\tseconds\tframe\tmanaged_r\tmanaged_u\tnative_r\tnative_u\tgfx\ttotal_allocated\ttotal_used";

	private const string logFormatStrDesc = "MemoryManager::desc\t{0:0.##}\t{1:0.##}\t{2:0.##}\t{3:0.##}\t{4:0.##}\t{5:0.##}\t{6:0.##}\t{7:0.##}\t{8:0.##}";

	private bool desclogStarted;

	private bool m_pause;

	private float checkInterval;

	public bool pause
	{
		set
		{
		}
	}

	public void Reset()
	{
	}

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	private void Start()
	{
	}

	[DebuggerHidden]
	private IEnumerator CheckMemory()
	{
		return null;
	}

	private void PrintReset()
	{
	}

	private void PrintGCCallState(float currentUsedMono)
	{
	}

	private void PrintMemroyUsageDesc()
	{
	}

	private void PrintMonoUsed(float heapsize)
	{
	}
}
