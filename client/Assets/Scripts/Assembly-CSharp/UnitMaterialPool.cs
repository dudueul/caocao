using System.Collections.Generic;
using UnityEngine;

public class UnitMaterialPool : SceneSingleton<UnitMaterialPool>
{
	private Dictionary<MaterialKey, Material> m_materials;

	public void Clear()
	{
	}

	public Material GetMaterial(MaterialKey key)
	{
		return null;
	}

	public void AddMaterial(MaterialKey key, Material mat)
	{
	}

	public Dictionary<MaterialKey, Material>.Enumerator GetEnumerator()
	{
		return default(Dictionary<MaterialKey, Material>.Enumerator);
	}

	protected override void OnDestroy()
	{
	}
}
