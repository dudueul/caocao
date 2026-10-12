using System;
using UnityEngine;

[Serializable]
public class SortingLayerExposed : MonoBehaviour
{
	[HideInInspector]
	public bool m_applyToChild;

	[HideInInspector]
	public string m_sortingLayer;

	[HideInInspector]
	public int m_sortingOrder;
}
