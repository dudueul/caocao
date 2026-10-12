using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine;

public class ParticleAdjustRenderQueue : MonoBehaviour
{
	public enum AdjustType
	{
		Front = 0,
		Back = 1
	}

	[StructLayout((LayoutKind)0, Size = 8)]
	private struct AdjustData
	{
		public string sortingLayer;

		public AdjustData(string layer)
		{
			sortingLayer = null;
		}
	}

	private const string sortingLayerName = "Default";

	[SerializeField]
	private string m_targetUIComponentName;

	private Dictionary<string, AdjustData> m_dicSaveData;

	private void OnEnable()
	{
	}

	private void InitSortingLayer()
	{
	}

	private void OnDestroy()
	{
	}
}
