using UnityEngine;

public class EffectPool : MonoBehaviour
{
	private Vector3 m_orgLocalPosition;

	private Quaternion m_orgLocalRotation;

	private Vector3 m_orgLocalScale;

	private Renderer[] m_renderers;

	private ParticleSystem[] m_particleSystems;

	private tk2dSpriteAnimator[] m_tk2dSpriteAnimators;

	private tk2dSprite[] m_tk2dSprites;

	private Color[] m_origSpriteColors;

	private bool m_inited;

	public void Init()
	{
	}

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	private void OnEnable()
	{
	}
}
