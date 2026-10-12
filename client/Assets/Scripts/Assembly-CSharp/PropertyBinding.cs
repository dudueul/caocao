using UnityEngine;

[ExecuteInEditMode]
public class PropertyBinding : MonoBehaviour
{
	public enum UpdateCondition
	{
		OnStart = 0,
		OnUpdate = 1,
		OnLateUpdate = 2,
		OnFixedUpdate = 3
	}

	public enum Direction
	{
		SourceUpdatesTarget = 0,
		TargetUpdatesSource = 1,
		BiDirectional = 2
	}

	public PropertyReference source;

	public PropertyReference target;

	public Direction direction;

	public UpdateCondition update;

	public bool editMode;

	private object mLastValue;

	private void Start()
	{
	}

	private void Update()
	{
	}

	private void LateUpdate()
	{
	}

	private void FixedUpdate()
	{
	}

	private void OnValidate()
	{
	}

	public void UpdateTarget()
	{
	}
}
