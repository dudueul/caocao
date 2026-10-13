using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public abstract class ComponentAction<T> : FsmStateAction where T : Component
	{
		protected GameObject cachedGameObject;

		protected T cachedComponent;

		protected Rigidbody rigidbody
		{
			get
			{
				return null;
			}
		}

		protected Rigidbody2D rigidbody2d
		{
			get
			{
				return null;
			}
		}

		protected Renderer renderer
		{
			get
			{
				return null;
			}
		}

		protected Animation animation
		{
			get
			{
				return null;
			}
		}

		protected AudioSource audio
		{
			get
			{
				return null;
			}
		}

		protected Camera camera
		{
			get
			{
				return null;
			}
		}

#if !UNITY_2019_1_OR_NEWER && !UNITY_6000_0_OR_NEWER
		protected GUIText guiText
		{
			get
			{
				return null;
			}
		}

		protected GUITexture guiTexture
		{
			get
			{
				return null;
			}
		}
#endif

		protected Light light
		{
			get
			{
				return null;
			}
		}

		protected bool UpdateCache(GameObject go)
		{
			return false;
		}

		protected bool UpdateCacheAddComponent(GameObject go)
		{
			return false;
		}

		protected void SendEvent(FsmEventTarget eventTarget, FsmEvent fsmEvent)
		{
		}
	}
}
