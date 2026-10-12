using UnityEngine;

[ExecuteInEditMode]
public class UISnapshotPoint : MonoBehaviour
{
	public bool isOrthographic;

	public float nearClip;

	public float farClip;

	public int fieldOfView;

	public float orthoSize;

	public Texture2D thumbnail;

	private void Start()
	{
	}
}
