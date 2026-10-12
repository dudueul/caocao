using System;
using System.Collections.Generic;
using UnityEngine;

public class tk2dUIManager : MonoBehaviour
{
	public static double version;

	public static int releaseId;

	private static tk2dUIManager instance;

	[SerializeField]
	private Camera uiCamera;

	private static List<tk2dUICamera> allCameras;

	private List<tk2dUICamera> sortedCameras;

	public LayerMask raycastLayerMask;

	private bool inputEnabled;

	public bool areHoverEventsTracked;

	private tk2dUIItem pressedUIItem;

	private tk2dUIItem overUIItem;

	private tk2dUITouch firstPressedUIItemTouch;

	private bool checkForHovers;

	[SerializeField]
	private bool useMultiTouch;

	private const int MAX_MULTI_TOUCH_COUNT = 5;

	private tk2dUITouch[] allTouches;

	private List<tk2dUIItem> prevPressedUIItemList;

	private tk2dUIItem[] pressedUIItems;

	private int touchCounter;

	private Vector2 mouseDownFirstPos;

	private const string MOUSE_WHEEL_AXES_NAME = "Mouse ScrollWheel";

	private tk2dUITouch primaryTouch;

	private tk2dUITouch secondaryTouch;

	private tk2dUITouch resultTouch;

	private tk2dUIItem hitUIItem;

	private RaycastHit hit;

	private Ray ray;

	private tk2dUITouch currTouch;

	private tk2dUIItem currPressedItem;

	private tk2dUIItem prevPressedItem;

	private Action OnAnyPress__BackingField;

	private Action OnInputUpdate__BackingField;

	private Action<float> OnScrollWheelChange__BackingField;

	public static tk2dUIManager Instance
	{
		get
		{
			return null;
		}
	}

	public static tk2dUIManager Instance__NoCreate
	{
		get
		{
			return null;
		}
	}

	public Camera UICamera
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool InputEnabled
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public tk2dUIItem PressedUIItem
	{
		get
		{
			return null;
		}
	}

	public tk2dUIItem[] PressedUIItems
	{
		get
		{
			return null;
		}
	}

	public bool UseMultiTouch
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public event Action OnAnyPress
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action OnInputUpdate
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<float> OnScrollWheelChange
	{
		add
		{
		}
		remove
		{
		}
	}

	public Camera GetUICameraForControl(GameObject go)
	{
		return null;
	}

	public static void RegisterCamera(tk2dUICamera cam)
	{
	}

	public static void UnregisterCamera(tk2dUICamera cam)
	{
	}

	private void SortCameras()
	{
	}

	private void Awake()
	{
	}

	private void HookUpLegacyCamera(Camera cam)
	{
	}

	private void Start()
	{
	}

	private void Setup()
	{
	}

	private void Update()
	{
	}

	private void CheckInputs()
	{
	}

	private void CheckMultiTouchInputs()
	{
	}

	private tk2dUIItem RaycastForUIItem(Vector2 screenPos)
	{
		return null;
	}

	public void OverrideClearAllChildrenPresses(tk2dUIItem item)
	{
	}
}
