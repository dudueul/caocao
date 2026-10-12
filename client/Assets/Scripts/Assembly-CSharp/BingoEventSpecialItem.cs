using CustomEvent;
using UnityEngine;

public class BingoEventSpecialItem : MonoBehaviour
{
	[SerializeField]
	protected UITexture texture;

	[SerializeField]
	protected GameObject get;

	[SerializeField]
	public ParticleSystem getFx;

	public BingoGridState state { get; protected set; }

	public void SetOrder(int order)
	{
	}

	public void UpdateState(BingoGridState gridState)
	{
	}
}
