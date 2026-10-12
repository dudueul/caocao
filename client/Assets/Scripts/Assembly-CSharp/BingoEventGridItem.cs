using CustomEvent;
using UnityEngine;

public class BingoEventGridItem : MonoBehaviour
{
	[SerializeField]
	protected UITexture texture;

	[SerializeField]
	protected GameObject get;

	[SerializeField]
	private GameObject bingo;

	[SerializeField]
	public ParticleSystem getFx;

	[SerializeField]
	public ParticleSystem bingoFx;

	private PlayMakerFSM m_getFSM;

	public BingoGridState state { get; protected set; }

	private void Start()
	{
	}

	public void SetOrder(int order)
	{
	}

	public void UpdateState(BingoGridState gridState)
	{
	}
}
