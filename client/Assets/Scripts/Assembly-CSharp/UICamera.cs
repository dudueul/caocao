using System;
using System.Collections.Generic;
using UnityEngine;

[ExecuteInEditMode]
public class UICamera : MonoBehaviour
{
	public enum ControlScheme
	{
		Mouse = 0,
		Touch = 1,
		Controller = 2
	}

	public enum ClickNotification
	{
		None = 0,
		Always = 1,
		BasedOnDelta = 2
	}

	public class MouseOrTouch
	{
		public Vector2 pos;

		public Vector2 lastPos;

		public Vector2 delta;

		public Vector2 totalDelta;

		public Camera pressedCam;

		public GameObject last;

		public GameObject current;

		public GameObject pressed;

		public GameObject dragged;

		public float pressTime;

		public float clickTime;

		public ClickNotification clickNotification;

		public bool touchBegan;

		public bool pressStarted;

		public bool dragStarted;

		public float deltaTime
		{
			get
			{
				return 0f;
			}
		}

		public bool isOverUI
		{
			get
			{
				return false;
			}
		}
	}

	public enum EventType
	{
		World_3D = 0,
		UI_3D = 1,
		World_2D = 2,
		UI_2D = 3
	}

	public delegate bool GetKeyStateFunc(KeyCode key);

	public delegate float GetAxisFunc(string name);

	public delegate void OnScreenResize();

	public delegate void OnCustomInput();

	public delegate void MoveDelegate(Vector2 delta);

	public delegate void VoidDelegate(GameObject go);

	public delegate void BoolDelegate(GameObject go, bool state);

	public delegate void FloatDelegate(GameObject go, float delta);

	public delegate void VectorDelegate(GameObject go, Vector2 delta);

	public delegate void ObjectDelegate(GameObject go, GameObject obj);

	public delegate void KeyCodeDelegate(GameObject go, KeyCode key);

	private struct DepthEntry
	{
		public int depth;

		public RaycastHit hit;

		public Vector3 point;

		public GameObject go;
	}

	public class Touch
	{
		public int fingerId;

		public TouchPhase phase;

		public Vector2 position;

		public int tapCount;
	}

	public delegate int GetTouchCountCallback();

	public delegate Touch GetTouchCallback(int index);

	public static BetterList<UICamera> list;

	public static GetKeyStateFunc GetKeyDown;

	public static GetKeyStateFunc GetKeyUp;

	public static GetKeyStateFunc GetKey;

	public static GetAxisFunc GetAxis;

	public static OnScreenResize onScreenResize;

	public EventType eventType;

	public bool eventsGoToColliders;

	public LayerMask eventReceiverMask;

	public bool debug;

	public static bool notifyInput;

	public bool useMouse;

	public bool useTouch;

	public bool allowMultiTouch;

	public bool useKeyboard;

	public bool useController;

	public bool stickyTooltip;

	public float tooltipDelay;

	public bool longPressTooltip;

	public float mouseDragThreshold;

	public float mouseClickThreshold;

	public float touchDragThreshold;

	public float touchClickThreshold;

	public float rangeDistance;

	public string scrollAxisName;

	public float scrollAxisRatio;

	public string verticalAxisName;

	public string horizontalAxisName;

	public bool commandClick;

	public KeyCode submitKey0;

	public KeyCode submitKey1;

	public KeyCode cancelKey0;

	public KeyCode cancelKey1;

	public static OnCustomInput onCustomInput;

	public static bool showTooltips;

	public static Vector2 lastTouchPosition;

	public static Vector3 lastWorldPosition;

	public static RaycastHit lastHit;

	public static UICamera current;

	public static Camera currentCamera;

	public static ControlScheme currentScheme;

	public static int currentTouchID;

	public static KeyCode currentKey;

	public static MouseOrTouch currentTouch;

	public static bool inputHasFocus;

	private static GameObject mGenericHandler;

	public static GameObject fallThrough;

	public static VoidDelegate onClick;

	public static VoidDelegate onDoubleClick;

	public static BoolDelegate onHover;

	public static BoolDelegate onPress;

	public static BoolDelegate onSelect;

	public static FloatDelegate onScroll;

	public static VectorDelegate onDrag;

	public static VoidDelegate onDragStart;

	public static ObjectDelegate onDragOver;

	public static ObjectDelegate onDragOut;

	public static VoidDelegate onDragEnd;

	public static ObjectDelegate onDrop;

	public static KeyCodeDelegate onKey;

	public static BoolDelegate onTooltip;

	public static MoveDelegate onMouseMove;

	private static GameObject mCurrentSelection;

	private static MouseOrTouch[] mMouse;

	private static GameObject mHover;

	public static MouseOrTouch controller;

	private static float mNextEvent;

	public static List<MouseOrTouch> activeTouches;

	private static List<int> mTouchIDs;

	private static int mWidth;

	private static int mHeight;

	private GameObject mTooltip;

	private Camera mCam;

	private float mTooltipTime;

	private float mNextRaycast;

	public static bool isDragging;

	public static GameObject hoveredObject;

	private static DepthEntry mHit;

	private static BetterList<DepthEntry> mHits;

	private static Plane m2DPlane;

	private static int mNotifying;

	private static bool mUsingTouchEvents;

	public static GetTouchCountCallback GetInputTouchCount;

	public static GetTouchCallback GetInputTouch;

	[Obsolete]
	public bool stickyPress
	{
		get
		{
			return false;
		}
	}

	public static Ray currentRay
	{
		get
		{
			return default(Ray);
		}
	}

	[Obsolete]
	public static GameObject genericEventHandler
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private bool handlesEvents
	{
		get
		{
			return false;
		}
	}

	public Camera cachedCamera
	{
		get
		{
			return null;
		}
	}

	public static bool isOverUI
	{
		get
		{
			return false;
		}
	}

	public static GameObject selectedObject
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	[Obsolete]
	public static int touchCount
	{
		get
		{
			return 0;
		}
	}

	public static int dragCount
	{
		get
		{
			return 0;
		}
	}

	public static Camera mainCamera
	{
		get
		{
			return null;
		}
	}

	public static UICamera eventHandler
	{
		get
		{
			return null;
		}
	}

	public static bool IsPressed(GameObject go)
	{
		return false;
	}

	public static int CountInputSources()
	{
		return 0;
	}

	private static int CompareFunc(UICamera a, UICamera b)
	{
		return 0;
	}

	private static Rigidbody FindRootRigidbody(Transform trans)
	{
		return null;
	}

	private static Rigidbody2D FindRootRigidbody2D(Transform trans)
	{
		return null;
	}

	public static bool Raycast(Vector3 inPos)
	{
		return false;
	}

	private static bool IsVisible(Vector3 worldPoint, GameObject go)
	{
		return false;
	}

	private static bool IsVisible(ref DepthEntry de)
	{
		return false;
	}

	public static bool IsHighlighted(GameObject go)
	{
		return false;
	}

	public static UICamera FindCameraForLayer(int layer)
	{
		return null;
	}

	private static int GetDirection(KeyCode up, KeyCode down)
	{
		return 0;
	}

	private static int GetDirection(KeyCode up0, KeyCode up1, KeyCode down0, KeyCode down1)
	{
		return 0;
	}

	private static int GetDirection(string axis)
	{
		return 0;
	}

	public static void Notify(GameObject go, string funcName, object obj)
	{
	}

	public static MouseOrTouch GetMouse(int button)
	{
		return null;
	}

	public static MouseOrTouch GetTouch(int id)
	{
		return null;
	}

	public static void RemoveTouch(int id)
	{
	}

	private void Awake()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void Start()
	{
	}

	private void Update()
	{
	}

	private void LateUpdate()
	{
	}

	public void ProcessMouse()
	{
	}

	public void ProcessTouches()
	{
	}

	private void ProcessFakeTouches()
	{
	}

	public void ProcessOthers()
	{
	}

	private void ProcessPress(bool pressed, float click, float drag)
	{
	}

	private void ProcessRelease(bool isMouse, float drag)
	{
	}

	public void ProcessTouch(bool pressed, bool released)
	{
	}

	public void ShowTooltip(bool val)
	{
	}
}
