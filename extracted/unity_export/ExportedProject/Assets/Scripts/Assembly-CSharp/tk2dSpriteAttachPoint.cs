using System.Collections.Generic;
using UnityEngine;

[ExecuteInEditMode]
public class tk2dSpriteAttachPoint : MonoBehaviour
{
	private tk2dBaseSprite sprite;

	public List<Transform> attachPoints;

	private static bool[] attachPointUpdated;

	public bool deactivateUnusedAttachPoints;

	private void Awake()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void UpdateAttachPointTransform(tk2dSpriteDefinition.AttachPoint attachPoint, Transform t)
	{
	}

	private void HandleSpriteChanged(tk2dBaseSprite spr)
	{
	}
}
